import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class AdminAuthenticationService {

  constructor(private http: HttpClient, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.http.get('api/user/authenticate')
      .map((response: any) => {
        if (response.role === 'ADMIN') {
          return true;
        } else {
          this.router.navigate(['/dashboard']);
          return false;
        }
      })
      .catch((error) => {
        this.router.navigate(['/login']);
        return Observable.of(false);
      });
  }

}
