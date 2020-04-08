import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MsgReceiverGroupMpService } from 'app/entities/msg-receiver-group-mp/msg-receiver-group-mp.service';
import { IMsgReceiverGroupMp, MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

describe('Service Tests', () => {
  describe('MsgReceiverGroupMp Service', () => {
    let injector: TestBed;
    let service: MsgReceiverGroupMpService;
    let httpMock: HttpTestingController;
    let elemDefault: IMsgReceiverGroupMp;
    let expectedResult: IMsgReceiverGroupMp | IMsgReceiverGroupMp[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MsgReceiverGroupMpService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MsgReceiverGroupMp(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MsgReceiverGroupMp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MsgReceiverGroupMp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MsgReceiverGroupMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            desc: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MsgReceiverGroupMp', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            desc: 'BBBBBB'
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

      it('should delete a MsgReceiverGroupMp', () => {
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
