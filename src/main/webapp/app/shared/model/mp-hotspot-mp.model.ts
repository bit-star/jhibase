import { Moment } from 'moment';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

export interface IMpHotspotMp {
  id?: number;
  name?: string;
  imageUrl?: string;
  pathUrl?: string;
  addTime?: Moment;
  orderNum?: number;
  remark?: string;
  fmpSubCompany?: IFmpSubCompanyMp;
}

export class MpHotspotMp implements IMpHotspotMp {
  constructor(
    public id?: number,
    public name?: string,
    public imageUrl?: string,
    public pathUrl?: string,
    public addTime?: Moment,
    public orderNum?: number,
    public remark?: string,
    public fmpSubCompany?: IFmpSubCompanyMp
  ) {}
}
