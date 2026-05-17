import { Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { WelcomeComponent } from "./welcome/welcome.component";
import { ListRestaurantsComponent } from "./list-restaurants/list-restaurants.component";
import { AuthGuard } from "./auth.guard";
import { ReservationComponent } from "./reservation/reservation.component";
import { ReviewsComponent } from "./reviews/reviews.component";
import { ReservationHistoryComponent } from "./reservation-history/reservation-history.component";

export const routes: Routes = [
  { path: '', component: WelcomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'restaurants', component: ListRestaurantsComponent},
  { path: 'restaurants/:id/review', component: ReviewsComponent, canActivate: [AuthGuard]},
  { path: 'restaurants/:id/reservation', component: ReservationComponent, canActivate: [AuthGuard]},
  { path: 'reservations', component: ReservationHistoryComponent, canActivate: [AuthGuard]}
];