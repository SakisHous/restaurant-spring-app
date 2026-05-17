import { Component, ElementRef, EventEmitter, Inject, Output, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule} from '@angular/material/icon';
import { AppService } from '../app.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatIconModule],
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent {
  cityInput: string ='';

  constructor(private router: Router) { }

  onSubmit() {
    
    if (!this.cityInput) {
      return
    }
    this.router.navigate(['/restaurants'], { queryParams: { city: this.cityInput } });
  }

}
