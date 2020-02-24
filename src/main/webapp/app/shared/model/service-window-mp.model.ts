import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IServiceWindowMp {
  id?: number;
  name?: string;
  userId?: string;
  link?: string;
  icon?: string;
  rank?: number;
  remark?: string;
  status?: Status;
  fmpSubCompany?: IFmpSubCompanyMp;
}

export class ServiceWindowMp implements IServiceWindowMp {
  constructor(
    public id?: number,
    public name?: string,
    public userId?: string,
    public link?: string,
    public icon?: string,
    public rank?: number,
    public remark?: string,
    public status?: Status,
    public fmpSubCompany?: IFmpSubCompanyMp
  ) {}
}
