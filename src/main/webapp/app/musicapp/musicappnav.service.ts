import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class NavigationService {
    // Observable string sources
    private navRequestSource = new Subject<string>();

    // Observable string streams
    navRequested$ = this.navRequestSource.asObservable();

    // Service message commands
    requestNavigation(navRequest: string) {
        this.navRequestSource.next(navRequest);
    }
}
