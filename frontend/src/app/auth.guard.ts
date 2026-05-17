import { Inject, Injectable } from "@angular/core";
import { AppService } from "./app.service";
import { JwtHelperService } from "@auth0/angular-jwt";
import { Router } from "@angular/router";


@Injectable({ providedIn: 'root' })
export class AuthGuard {

  constructor(
    private appService: AppService = Inject(AppService),
    private jwtHelper: JwtHelperService = Inject(JwtHelperService),
    private router: Router
    ) { }

    canActivate() {
      if (
        this.appService.isLoggedIn.value &&
        !this.jwtHelper.isTokenExpired(localStorage.getItem('auth_token'))
      ) {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    }
}