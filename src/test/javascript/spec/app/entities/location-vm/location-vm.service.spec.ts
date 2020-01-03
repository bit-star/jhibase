import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LocationVMService } from 'app/entities/location-vm/location-vm.service';
import { ILocationVM, LocationVM } from 'app/shared/model/location-vm.model';
import { WorkLogType } from 'app/shared/model/enumerations/work-log-type.model';

describe('Service Tests', () => {
  describe('LocationVM Service', () => {
    let injector: TestBed;
    let service: LocationVMService;
    let httpMock: HttpTestingController;
    let elemDefault: ILocationVM;
    let expectedResult: ILocationVM | ILocationVM[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LocationVMService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new LocationVM(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        0,
        WorkLogType.HealthRoom,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a LocationVM', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new LocationVM())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LocationVM', () => {
        const returnedFromService = Object.assign(
          {
            stationId: 1,
            groupName: 'BBBBBB',
            stationName: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            accuracy: 'BBBBBB',
            address: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            road: 'BBBBBB',
            netType: 'BBBBBB',
            operatorType: 'BBBBBB',
            ddUserName: 'BBBBBB',
            ddUserId: 1,
            ddUserPhone: 'BBBBBB',
            isFinish: true,
            workLogMainId: 1,
            workLogType: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of LocationVM', () => {
        const returnedFromService = Object.assign(
          {
            stationId: 1,
            groupName: 'BBBBBB',
            stationName: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            accuracy: 'BBBBBB',
            address: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            road: 'BBBBBB',
            netType: 'BBBBBB',
            operatorType: 'BBBBBB',
            ddUserName: 'BBBBBB',
            ddUserId: 1,
            ddUserPhone: 'BBBBBB',
            isFinish: true,
            workLogMainId: 1,
            workLogType: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createdDate: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a LocationVM', () => {
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
