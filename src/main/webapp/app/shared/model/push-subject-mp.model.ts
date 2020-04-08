import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

export interface IPushSubjectMp {
  id?: number;
  agentId?: number;
  name?: string;
  agentGroupId?: string;
  titleColor?: string;
  remark?: string;
  fmpSubCompany?: IFmpSubCompanyMp;
}

export class PushSubjectMp implements IPushSubjectMp {
  constructor(
    public id?: number,
    public agentId?: number,
    public name?: string,
    public agentGroupId?: string,
    public titleColor?: string,
    public remark?: string,
    public fmpSubCompany?: IFmpSubCompanyMp
  ) {}
}
