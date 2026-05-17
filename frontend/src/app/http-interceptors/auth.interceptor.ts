import { Inject, Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private jwtHelper: JwtHelperService = Inject(JwtHelperService)) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authToken = localStorage.getItem('auth_token');

    if (authToken && !this.jwtHelper.isTokenExpired(authToken)) {
      const authReq = request.clone({
      headers: request.headers
        .set('Content-Type', 'application/json')
        .set('Authorization', 'Bearer ' +  authToken)
     });
     return next.handle(authReq);
    }
    // else return the request at it is
    return next.handle(request);
  }
}
