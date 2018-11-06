/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhtestTestModule } from '../../../test.module';
import { MusicianComponent } from 'app/entities/musician/musician.component';
import { MusicianService } from 'app/entities/musician/musician.service';
import { Musician } from 'app/shared/model/musician.model';

describe('Component Tests', () => {
    describe('Musician Management Component', () => {
        let comp: MusicianComponent;
        let fixture: ComponentFixture<MusicianComponent>;
        let service: MusicianService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhtestTestModule],
                declarations: [MusicianComponent],
                providers: []
            })
                .overrideTemplate(MusicianComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MusicianComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MusicianService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Musician(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.musicians[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
