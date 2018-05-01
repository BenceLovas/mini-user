import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { AdminDashboardComponent } from '../admin-dashboard/admin-dashboard.component';
import {LoginComponent} from '../login/login.component';
import {UserAuthenticationService} from '../service/user-authentication.service';
import {AdminAuthenticationService} from '../service/admin-authentication.service';
import {PageNotFoundComponent} from '../page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [UserAuthenticationService] },
  { path: 'admin/dashboard', component: AdminDashboardComponent, canActivate: [AdminAuthenticationService] },
  { path: '', canActivate: [UserAuthenticationService], redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
export const routingComponents = [
  DashboardComponent,
  AdminDashboardComponent,
  LoginComponent,
  PageNotFoundComponent,
];
