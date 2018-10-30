import { Component, NgModule, CUSTOM_ELEMENTS_SCHEMA, OnInit, ElementRef, Renderer, AfterViewInit } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from 'app/shared';
import { JhiEventManager } from 'ng-jhipster';
import { LoginService, StateStorageService } from 'app/core';
import { Router } from '@angular/router';
import { LoginModalService, Principal, Account } from 'app/core';

@Component({
    selector: 'jhi-login',
    templateUrl: './login.component.html'
})
export class JhiLoginComponent implements AfterViewInit {
        authenticationError: boolean;
        password: string;
        rememberMe: boolean;
        username: string;
        credentials: any;
        account: Account;

        constructor(
            private principal: Principal,
            private eventManager: JhiEventManager,
            private loginService: LoginService,
            private stateStorageService: StateStorageService,
            private elementRef: ElementRef,
            private renderer: Renderer,
            private router: Router,
        ) {
            this.credentials = {};
        }

        isAuthenticated() {
            return this.principal.isAuthenticated();
        }
        
        ngAfterViewInit() {
            this.principal.identity().then(account => {
                this.account = account;
            });
            this.registerAuthenticationSuccess();
        }
        
        registerAuthenticationSuccess() {
            this.eventManager.subscribe('authenticationSuccess', message => {
                this.principal.identity().then(account => {
                    this.account = account;
                });
            });
        }
        
        cancel() {
            this.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            this.authenticationError = false;
        }
        
        logout() {
            this.loginService.logout();
        } 
        
        login() {
            this.loginService
                .login({
                    username: this.username,
                    password: this.password,
                    rememberMe: this.rememberMe
                })
                .then(() => {
                    this.authenticationError = false;
                    if (this.router.url === '/register' || /^\/activate\//.test(this.router.url) || /^\/reset\//.test(this.router.url)) {
                        this.router.navigate(['']);
                    }

                    this.eventManager.broadcast({
                        name: 'authenticationSuccess',
                        content: 'Sending Authentication Success'
                    });

                    // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                    // since login is succesful, go to stored previousState and clear previousState
                    const redirect = this.stateStorageService.getUrl();
                    if (redirect) {
                        this.stateStorageService.storeUrl(null);
                        this.router.navigate([redirect]);
                    }
                })
                .catch(() => {
                    this.authenticationError = true;
                });
        }

        register() {
            this.router.navigate(['/register']);
        }

        requestResetPassword() {
            this.router.navigate(['/reset', 'request']);
        }

}
