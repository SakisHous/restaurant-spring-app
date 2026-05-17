import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationLayoutComponent } from './application-layout/application-layout.component';
import { Restaurant } from './interfaces/restaurant';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ApplicationLayoutComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'restaurant-front';
  restaurants: Restaurant[] = [];

  constructor(private appService: AppService = Inject(AppService)) { }
}
