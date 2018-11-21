import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';
import { NavigationService } from 'app/musicapp/musicappnav.service';
import { Subscription } from 'rxjs';
import { OnDestroy } from '@angular/core';

@Component({
    selector: 'tracklist',
    templateUrl: './tracklist.component.html'
})
export class TracklistComponent implements OnInit {
    ngOnInit(): void {}

    constructor(private navigationService: NavigationService) {
        /*navigationService.navExecuted$.subscribe(
          astronaut => {
            //this.history.push(`${astronaut} confirmed the mission`);
          });*/
    }

    onBackToBandViewerClicked() {
        this.navigationService.requestNavigation('select band clicked');
    }
}
