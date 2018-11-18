import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavigationService } from 'app/musicapp/musicappnav.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'musicapp',
    templateUrl: './musicapp.component.html',
    providers: [NavigationService]
})
export class MusicAppComponent implements OnInit {
    subscription: Subscription;

    constructor(private router: Router, private route: ActivatedRoute, private navigationService: NavigationService) {
        this.subscription = navigationService.navRequested$.subscribe(navrequest => {
            this.router.navigate([{ outlets: { contentHolder: ['bandviewer'] } }], { relativeTo: this.route });
        });
    }

    ngOnInit(): void {
        this.router.navigate([{ outlets: { bandListHolder: ['bandlist'], albumListHolder: ['albumlist'] } }], { relativeTo: this.route });
    }
}
