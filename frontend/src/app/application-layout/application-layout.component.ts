import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterModule } from '@angular/router';
import { AppService } from '../app.service';
import { Restaurant } from '../interfaces/restaurant';

@Component({
  selector: 'app-application-layout',
  standalone: true,
  imports: [ 
    CommonModule, 
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule,
    MatInputModule,
    RouterModule
  ],
  templateUrl: './application-layout.component.html',
  styleUrls: ['./application-layout.component.css']
})
export class ApplicationLayoutComponent {
  isLoggedIn$ = this.appService.isLoggedIn;
  username$ = this.appService.username;

  constructor(private appService: AppService = Inject(AppService), private router: Router) { }

  logout() {
    this.appService.logout();
    this.router.navigate(['']);
  }
}
