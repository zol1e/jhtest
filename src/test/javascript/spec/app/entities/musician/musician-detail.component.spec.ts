/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhtestTestModule } from '../../../test.module';
import { MusicianDetailComponent } from 'app/entities/musician/musician-detail.component';
import { Musician } from 'app/shared/model/musician.model';

describe('Component Tests', () => {
    describe('Musician Management Detail Component', () => {
        let comp: MusicianDetailComponent;
        let fixture: ComponentFixture<MusicianDetailComponent>;
        const route = ({ data: of({ musician: new Musician(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhtestTestModule],
                declarations: [MusicianDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MusicianDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MusicianDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.musician).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
