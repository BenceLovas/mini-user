import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { RegistrationFormComponent } from './login/registration-form/registration-form.component';
import { MaterialModule } from './module/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from './service/user.service';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule, routingComponents } from './module/app-routing.module';
import { UserAuthenticationService } from './service/user-authentication.service';
import { AdminAuthenticationService } from './service/admin-authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    UserService,
    UserAuthenticationService,
    AdminAuthenticationService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
