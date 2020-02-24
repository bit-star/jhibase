import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';
import { IFmpSubCompanyMp, FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

describe('Service Tests', () => {
  describe('FmpSubCompanyMp Service', () => {
    let injector: TestBed;
    let service: FmpSubCompanyMpService;
    let httpMock: HttpTestingController;
    let elemDefault: IFmpSubCompanyMp;
    let expectedResult: IFmpSubCompanyMp | IFmpSubCompanyMp[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FmpSubCompanyMpService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new FmpSubCompanyMp(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FmpSubCompanyMp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new FmpSubCompanyMp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FmpSubCompanyMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            code: 'BBBBBB',
            adminGroupId: 'BBBBBB',
            ifPublic: 'BBBBBB',
            styleId: 'BBBBBB',
            isDeleted: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FmpSubCompanyMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            code: 'BBBBBB',
            adminGroupId: 'BBBBBB',
            ifPublic: 'BBBBBB',
            styleId: 'BBBBBB',
            isDeleted: true
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

      it('should delete a FmpSubCompanyMp', () => {
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
