import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProcessMsgTaskMpService } from 'app/entities/process-msg-task-mp/process-msg-task-mp.service';
import { IProcessMsgTaskMp, ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

describe('Service Tests', () => {
  describe('ProcessMsgTaskMp Service', () => {
    let injector: TestBed;
    let service: ProcessMsgTaskMpService;
    let httpMock: HttpTestingController;
    let elemDefault: IProcessMsgTaskMp;
    let expectedResult: IProcessMsgTaskMp | IProcessMsgTaskMp[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProcessMsgTaskMpService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProcessMsgTaskMp(0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProcessMsgTaskMp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProcessMsgTaskMp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProcessMsgTaskMp', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProcessMsgTaskMp', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProcessMsgTaskMp', () => {
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
