import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LocationFlatService } from 'app/entities/location-flat/location-flat.service';
import { ILocationFlat, LocationFlat } from 'app/shared/model/location-flat.model';
import { WorkLogType } from 'app/shared/model/enumerations/work-log-type.model';

describe('Service Tests', () => {
  describe('LocationFlat Service', () => {
    let injector: TestBed;
    let service: LocationFlatService;
    let httpMock: HttpTestingController;
    let elemDefault: ILocationFlat;
    let expectedResult: ILocationFlat | ILocationFlat[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LocationFlatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new LocationFlat(
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

      it('should create a LocationFlat', () => {
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
          .create(new LocationFlat())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LocationFlat', () => {
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

      it('should return a list of LocationFlat', () => {
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

      it('should delete a LocationFlat', () => {
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
