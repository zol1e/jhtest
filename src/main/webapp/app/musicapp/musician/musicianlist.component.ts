import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { NavigationService } from 'app/musicapp/musicappnav.service';

@Component({
    selector: 'musicianlist',
    templateUrl: './musicianlist.component.html'
})
export class MusicianlistComponent implements OnInit {
    ngOnInit(): void {}

    constructor(private navigationService: NavigationService) {
        /*navigationService.navExecuted$.subscribe(
          astronaut => {
            //this.history.push(`${astronaut} confirmed the mission`);
          });*/
    }

    onSelectBandClicked() {
        this.navigationService.requestNavigation('2');
    }
}
