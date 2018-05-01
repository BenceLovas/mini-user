import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

@Injectable()
export class UserAuthenticationService implements CanActivate {

  constructor(private http: HttpClient, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.http.get('api/user/authenticate')
      .map((response: any) => {
        if (response.role === 'USER') {
          return true;
        } else if (response.role === 'ADMIN') {
          this.router.navigate(['/admin/dashboard']);
          return true;
        }
      })
      .catch((error) => {
        this.router.navigate(['/login']);
        return Observable.of(false);
      });
  }

}
