import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Restaurant } from "../interfaces/restaurant";

@Injectable({
  providedIn: 'root',
})
export class RestaurantDetailsSharedService {
  restaurant: Restaurant | undefined;

  setRestaurantDetails(restaurant: Restaurant) {
    this.restaurant = restaurant;
  }

  getRestaurantDetails() {
    return this.restaurant;
  }
}