import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import {Router} from '@angular/router';
import {FormErrorStateMatcher} from '../shared/formErrorStateMatcher';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  @Input() public isPanelOpen;
  @Output() public toggleEvent = new EventEmitter();
  registrationForm: FormGroup;
  failedEmail: string;
  matcher = new FormErrorStateMatcher();

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router) {
  }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      'username': ['', Validators.compose([
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(30),
      ])],
      'password': ['', Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(20),
      ])],
      'email': ['', Validators.compose([
        Validators.required,
        Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/),
        this.validateEmail.bind(this),
      ])],
    });
  }

  validateEmail(control: FormControl) {
    if (control.value === this.failedEmail) {
      return { emailTaken: true };
    } else {
      return null;
    }
  }

  onSubmit(form: any) {
    const user: User = {
      name: form.username.trim(),
      password: form.password.trim(),
      email: form.email.trim()
    };
    this.userService
      .registerUser(user)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['/dashboard']);
        },
        error => {
          console.log(error);
          this.failedEmail = error.email;
          this.registrationForm.controls['email'].updateValueAndValidity();
        }
      );
  }

  togglePanel() {
    this.toggleEvent.emit(this.isPanelOpen);
  }

}
