import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationHistoryComponent } from './reservation-history.component';

describe('ReservationHistoryComponent', () => {
  let component: ReservationHistoryComponent;
  let fixture: ComponentFixture<ReservationHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ReservationHistoryComponent]
    });
    fixture = TestBed.createComponent(ReservationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
