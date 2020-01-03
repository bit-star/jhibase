import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationVMDetailComponent } from 'app/entities/location-vm/location-vm-detail.component';
import { LocationVM } from 'app/shared/model/location-vm.model';

describe('Component Tests', () => {
  describe('LocationVM Management Detail Component', () => {
    let comp: LocationVMDetailComponent;
    let fixture: ComponentFixture<LocationVMDetailComponent>;
    const route = ({ data: of({ locationVM: new LocationVM(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationVMDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LocationVMDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LocationVMDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load locationVM on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.locationVM).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
