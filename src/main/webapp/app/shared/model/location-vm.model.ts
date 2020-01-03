import { Moment } from 'moment';
import { WorkLogType } from 'app/shared/model/enumerations/work-log-type.model';

export interface ILocationVM {
  id?: number;
  stationId?: number;
  groupName?: string;
  stationName?: string;
  longitude?: number;
  latitude?: number;
  accuracy?: string;
  address?: string;
  province?: string;
  city?: string;
  district?: string;
  road?: string;
  netType?: string;
  operatorType?: string;
  ddUserName?: string;
  ddUserId?: number;
  ddUserPhone?: string;
  isFinish?: boolean;
  workLogMainId?: number;
  workLogType?: WorkLogType;
  createdDate?: Moment;
}

export class LocationVM implements ILocationVM {
  constructor(
    public id?: number,
    public stationId?: number,
    public groupName?: string,
    public stationName?: string,
    public longitude?: number,
    public latitude?: number,
    public accuracy?: string,
    public address?: string,
    public province?: string,
    public city?: string,
    public district?: string,
    public road?: string,
    public netType?: string,
    public operatorType?: string,
    public ddUserName?: string,
    public ddUserId?: number,
    public ddUserPhone?: string,
    public isFinish?: boolean,
    public workLogMainId?: number,
    public workLogType?: WorkLogType,
    public createdDate?: Moment
  ) {
    this.isFinish = this.isFinish || false;
  }
}
