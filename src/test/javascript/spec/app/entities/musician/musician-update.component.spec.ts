/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhtestTestModule } from '../../../test.module';
import { MusicianUpdateComponent } from 'app/entities/musician/musician-update.component';
import { MusicianService } from 'app/entities/musician/musician.service';
import { Musician } from 'app/shared/model/musician.model';

describe('Component Tests', () => {
    describe('Musician Management Update Component', () => {
        let comp: MusicianUpdateComponent;
        let fixture: ComponentFixture<MusicianUpdateComponent>;
        let service: MusicianService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhtestTestModule],
                declarations: [MusicianUpdateComponent]
            })
                .overrideTemplate(MusicianUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MusicianUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MusicianService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Musician(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.musician = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Musician();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.musician = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
