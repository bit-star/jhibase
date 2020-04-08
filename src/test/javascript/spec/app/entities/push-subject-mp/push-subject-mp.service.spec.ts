import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PushSubjectMpService } from 'app/entities/push-subject-mp/push-subject-mp.service';
import { IPushSubjectMp, PushSubjectMp } from 'app/shared/model/push-subject-mp.model';

describe('Service Tests', () => {
  describe('PushSubjectMp Service', () => {
    let injector: TestBed;
    let service: PushSubjectMpService;
    let httpMock: HttpTestingController;
    let elemDefault: IPushSubjectMp;
    let expectedResult: IPushSubjectMp | IPushSubjectMp[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PushSubjectMpService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PushSubjectMp(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PushSubjectMp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PushSubjectMp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PushSubjectMp', () => {
        const returnedFromService = Object.assign(
          {
            agentId: 1,
            name: 'BBBBBB',
            agentGroupId: 'BBBBBB',
            titleColor: 'BBBBBB',
            remark: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PushSubjectMp', () => {
        const returnedFromService = Object.assign(
          {
            agentId: 1,
            name: 'BBBBBB',
            agentGroupId: 'BBBBBB',
            titleColor: 'BBBBBB',
            remark: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PushSubjectMp', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
