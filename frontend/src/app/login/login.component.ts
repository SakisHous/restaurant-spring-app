import { Component, EventEmitter, Inject, Input, Output, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { UserLogin } from '../interfaces/user-login';
import { AppService } from '../app.service';
import { Credentials } from '../interfaces/credentials';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, 
    ReactiveFormsModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() title = 'Login';
  @Input() userLoginInput: UserLogin | undefined;

  form = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['userLoginInput']?.currentValue) {
      this.form.patchValue(changes['userLoginInput'].currentValue);
    }
  }

  constructor(
    private appService: AppService = Inject(AppService),
    private jwtHelperService: JwtHelperService = Inject(JwtHelperService),
    private router: Router) { 
    }

  onSubmit() {
    this.appService.login(this.form.value as Credentials).subscribe({
      next: (response) => {
        localStorage.setItem('auth_token', response.token);
        const decoded_token = this.jwtHelperService.decodeToken(response.token);
        this.appService.isLoggedIn.next(true);
        this.appService.username.next(decoded_token.sub);
        //console.log(decoded_token);
      },
      error: (error) => {
        console.log('ERROR:', error);
      }
    })
    this.form.reset();
    this.router.navigate(['']);
  }

}
