import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  errorHandler(response: HttpErrorResponse) {
    return Observable.throw(response.error);
  }

  registerUser(user: User) {
    return this.http.post('api/user', user).catch(this.errorHandler);
  }

  loginUser(user: User) {
    return this.http.post('api/user/login', user).catch(this.errorHandler);
  }

  getUsers() {
    return this.http.get<User[]>('api/users').catch(this.errorHandler);
  }
}
