import { Component, EventEmitter, Inject, Input, Output, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../interfaces/user';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { AppService } from '../app.service';
import { UserRegisterDto } from '../interfaces/user-register-dto';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  @Input() title = 'Register';
  @Input() userInput: User | undefined;

  form = new FormGroup({
    id: new FormControl(0),
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
    firstname: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  constructor(private appService: AppService = Inject(AppService), private router: Router) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['personInput']?.currentValue) {
      this.form.patchValue(changes['personInput'].currentValue);
    }
  }

  onSubmit() {
    this.appService.register(this.form.value as UserRegisterDto).subscribe({
      next: (response) => {
        console.log(response);
        this.form.reset();
        this.router.navigate(['/login']);
      }, 
      error: (err) => {
        console.log(err);
      }
    })
    
  }
}
