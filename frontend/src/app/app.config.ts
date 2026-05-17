import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { JwtModule } from '@auth0/angular-jwt';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router'
import { routes } from './app.routes';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { authInterceptorProvider } from './token.provider';

export function tokenGetter() {
  return localStorage.getItem('auth_token');
}

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(
      JwtModule.forRoot({
        config: {
          tokenGetter: tokenGetter,
        }
      })

    ),

    provideAnimations(), 
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    authInterceptorProvider
  ]
};
