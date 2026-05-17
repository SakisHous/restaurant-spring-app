import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AppService } from '../app.service';
import { Restaurant } from '../interfaces/restaurant';
import { RestaurantDetailsSharedService } from '../shared-services/restaurant-details';

@Component({
  selector: 'app-list-restaurants',
  standalone: true,
  imports: [
    CommonModule, 
    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './list-restaurants.component.html',
  styleUrls: ['./list-restaurants.component.css']
})
export class ListRestaurantsComponent {
  cityInput: string = '';
  restaurants: Restaurant[] = [];

  constructor(private route: ActivatedRoute, private appService: AppService = Inject(AppService), private restaurantDetails: RestaurantDetailsSharedService) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.cityInput = params['city'];
      this.cityInput = this.cityInput.charAt(0).toUpperCase() + this.cityInput.slice(1).toLowerCase();

      this.appService.getRestaurantsByCity(this.cityInput).subscribe((restaurants) => {
        this.restaurants = restaurants;
      })
    })
  }

  sendRestaurantDetails(restaurant: Restaurant) {
    this.restaurantDetails.setRestaurantDetails(restaurant);
  }
}
