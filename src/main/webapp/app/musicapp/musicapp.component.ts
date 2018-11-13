import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'musicapp',
    templateUrl: './musicapp.component.html'
})
export class MusicAppComponent implements OnInit {
    constructor(private router: Router, private route: ActivatedRoute) {}

    ngOnInit(): void {
        this.router.navigate([{ outlets: { bandListHolder: ['bandlist'], contentHolder: ['bandinfo'] } }], { relativeTo: this.route });
    }
}
