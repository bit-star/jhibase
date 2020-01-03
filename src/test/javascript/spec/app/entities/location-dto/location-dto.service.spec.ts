import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LocationDTOService } from 'app/entities/location-dto/location-dto.service';
import { ILocationDTO, LocationDTO } from 'app/shared/model/location-dto.model';
import { WorkLogType } from 'app/shared/model/enumerations/work-log-type.model';

describe('Service Tests', () => {
  describe('LocationDTO Service', () => {
    let injector: TestBed;
    let service: LocationDTOService;
    let httpMock: HttpTestingController;
    let elemDefault: ILocationDTO;
    let expectedResult: ILocationDTO | ILocationDTO[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LocationDTOService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new LocationDTO(
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

      it('should create a LocationDTO', () => {
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
          .create(new LocationDTO())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LocationDTO', () => {
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

      it('should return a list of LocationDTO', () => {
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

      it('should delete a LocationDTO', () => {
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
