import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user';
import {MatPaginator, MatSnackBar, MatSort, MatTableDataSource} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  displayedColumns = ['id', 'name', 'email', 'actions'];
  dataSource: MatTableDataSource<User>;
  filter = '';
  afterDelete = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private userService: UserService,
    private snackBar: MatSnackBar,
    private router: Router) {
  }

  ngOnInit() {
    this.userService.getUsers()
      .subscribe(
        data => this.fillTable(data),
        error => console.log(error)
      );
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, '',{
      duration: 2500,
    });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.filter = filterValue;
    this.dataSource.filter = filterValue;
  }

  logout() {
    this.userService.logoutUser()
      .subscribe(
        data => this.router.navigate(['/login']),
        error => console.log(error)
      );
  }

  deleteUser(user) {
    this.userService.deleteUser(user)
      .subscribe(
        data => {
          this.afterDelete = true;
          this.fillTable(data);
          this.afterDelete = false;
          this.openSnackBar(`${user.name} successfully deleted.`);
        },
        error => {
          console.log(error);
          this.openSnackBar('Something went wrong. Please try again.');
        },
      );
  }

  fillTable(data) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.sort = this.sort;
    this.dataSource.sortingDataAccessor = (userData, sortHeaderID) => {
      if (!this.afterDelete) {
        this.paginator.pageIndex = 0;
      }
      if (typeof userData[sortHeaderID] === 'string') {
        return userData[sortHeaderID].toLocaleLowerCase();
      }
      return userData[sortHeaderID];
    };
    this.dataSource.filterPredicate = (userData: User, filter: string): boolean => {
      if (!this.afterDelete) {
        this.paginator.pageIndex = 0;
      }
      const reducedUserData = {'id': userData.id, 'name': userData.name, 'email': userData.email};
      const accumulator = (currentTerm, key) => currentTerm + reducedUserData[key];
      const userDataStr = Object.keys(userData).reduce(accumulator, '').toLowerCase();

      const transformedFilter = filter.trim().toLowerCase();

      return userDataStr.indexOf(transformedFilter) !== -1;
    };
    this.dataSource.paginator = this.paginator;
    if (this.dataSource.paginator.pageSize * this.dataSource.paginator.pageIndex >= data.length) {
      this.dataSource.paginator.previousPage();
    }
    this.applyFilter(this.filter);
  }

}
