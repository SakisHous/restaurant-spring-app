import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Restaurant } from './interfaces/restaurant';
import { Credentials } from './interfaces/credentials';
import { JwtToken } from './interfaces/jwt-token';
import { BehaviorSubject } from 'rxjs';
import { UserRegisterDto } from './interfaces/user-register-dto';
import { ReservationDto } from './interfaces/reservation-dto';
import { ReviewDto } from './interfaces/review-dto';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  isLoggedIn = new BehaviorSubject<boolean>(false);
  username = new BehaviorSubject<string>('');

  constructor(private http: HttpClient = Inject(HttpClient)) { }

  getRestaurantsByCity(city: string) {
    return this.http.get<Restaurant[]>(`http://localhost:8080/api/restaurants?city=${city}`);
  }

  getReservations() {
    const token = localStorage.getItem('auth_token');

    return this.http.get<ReservationDto[]>(`http://localhost:8080/api/reservations`);
  }

  insertReservation(reservationDto: ReservationDto) {
    return this.http.post<ReservationDto>(`http://localhost:8080/api/restaurants/${reservationDto.restaurantId}/reservation`, reservationDto);
  }

  insertReview(reviewDto: ReviewDto) {
    return this.http.post<ReviewDto>(`http://localhost:8080/api/restaurants/${reviewDto.restaurantId}/review`, reviewDto);
  }

  login(credentials: Credentials) {
    return this.http.post<JwtToken>(`http://localhost:8080/api/v1/auth/authenticate`, credentials);
  }

  register(userRegisterDto: UserRegisterDto) {
    return this.http.post<JwtToken>(`http://localhost:8080/api/v1/auth/register`, userRegisterDto);
  }

  logout() {
    //console.log('Remove auth_token');
    this.isLoggedIn.next(false);
    this.username.next('');
    localStorage.removeItem('auth_token');
  }
}
