/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhtestTestModule } from '../../../test.module';
import { MusicianDeleteDialogComponent } from 'app/entities/musician/musician-delete-dialog.component';
import { MusicianService } from 'app/entities/musician/musician.service';

describe('Component Tests', () => {
    describe('Musician Management Delete Component', () => {
        let comp: MusicianDeleteDialogComponent;
        let fixture: ComponentFixture<MusicianDeleteDialogComponent>;
        let service: MusicianService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhtestTestModule],
                declarations: [MusicianDeleteDialogComponent]
            })
                .overrideTemplate(MusicianDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MusicianDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MusicianService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
