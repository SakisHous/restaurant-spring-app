import { Component, Inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { AppService } from '../app.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewDto } from '../interfaces/review-dto';
import { Restaurant } from '../interfaces/restaurant';
import { RestaurantDetailsSharedService } from '../shared-services/restaurant-details';

@Component({
  selector: 'app-reviews',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatCardModule, MatButtonModule],
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {
  restaurant: Restaurant | undefined;
  id: number | undefined;

  constructor(
    private appService: AppService = Inject(AppService),
    private route: ActivatedRoute,
    private router: Router,
    private restaurantDetails: RestaurantDetailsSharedService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.restaurant = this.restaurantDetails.getRestaurantDetails();
  }

  onSubmit(form: any) {
    form.value.restaurantId = this.id;
    console.log(form.value);
    this.appService.insertReview(form.value as ReviewDto).subscribe({
      next: (response) => {
        if (response.reviewId) {
          this.router.navigate(['']);
        }
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
}
