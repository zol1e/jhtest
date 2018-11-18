import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'bandviewer',
    templateUrl: './bandviewer.component.html'
})
export class BandviewerComponent implements OnInit {
    constructor(private router: Router, private route: ActivatedRoute) {}

    ngOnInit(): void {
        this.router.navigate([{ outlets: { bandInfoHolder: ['bandinfo'], musiciansHolder: ['musicianlist'] } }], {
            relativeTo: this.route
        });
    }
}
