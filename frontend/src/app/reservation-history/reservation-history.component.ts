import { Component, Inject, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { AppService } from '../app.service';
import { ReservationDto } from '../interfaces/reservation-dto';
import { MatSort } from '@angular/material/sort';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-reservation-history',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './reservation-history.component.html',
  styleUrls: ['./reservation-history.component.css']
})
export class ReservationHistoryComponent  {
  @ViewChild(MatSort) sort!: MatSort;
  reservations: ReservationDto[] = [];
  dataSource!: MatTableDataSource<ReservationDto>;

  displayedColumns: string[] = ['restaurantName', 'reservationDate', 'status', 'partySize'];

  constructor(private appService: AppService = Inject(AppService)) { }

  ngOnInit(): void {
    this.appService.getReservations().subscribe({
      next: (reservations) => {
        this.reservations = reservations;
        this.dataSource = new MatTableDataSource<ReservationDto>(this.reservations);
        this.dataSource.sort = this.sort;
      }, 
      error: (err) => {
        console.log(err);
      }
    });

  }

  
  
}
