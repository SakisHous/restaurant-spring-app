import { Component, Inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { AppService } from '../app.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationDto } from '../interfaces/reservation-dto';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [CommonModule, 
    ReactiveFormsModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatDatepickerModule, 
    MatNativeDateModule, 
    MatButtonModule
  ],
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  @Input() title = 'Make a Reservation';
  id: number | undefined;
  
  form = new FormGroup({
    restaurantId: new FormControl(0),
    reservationDate: new FormControl('', Validators.required),
    partySize: new FormControl('', Validators.required),
  });

  constructor(
    private appService: AppService = Inject(AppService),
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  onSubmit() {
    this.form.value.restaurantId = this.id;
    this.appService.insertReservation(this.form.value as ReservationDto).subscribe({
      next:(response) => {
        //console.log(response);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
}
