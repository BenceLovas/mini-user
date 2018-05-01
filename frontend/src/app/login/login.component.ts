import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public isLoginPanelOpen = true;
  public isRegistrationPanelOpen = false;

  toggleLogin(isPanelOpen) {
    this.isLoginPanelOpen = !isPanelOpen;
    if (this.isLoginPanelOpen) {
      this.isRegistrationPanelOpen = false;
    }
  }

  toggleRegistration(isPanelOpen) {
    this.isRegistrationPanelOpen = !isPanelOpen;
    if (this.isRegistrationPanelOpen) {
      this.isLoginPanelOpen = false;
    }
  }

  constructor() { }

  ngOnInit() {
  }

}
