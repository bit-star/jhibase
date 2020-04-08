import { IUucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

export interface IMsgReceiverGroupMp {
  id?: number;
  name?: string;
  desc?: string;
  uucDepartmentTrees?: IUucDepartmentTreeMp[];
  uucUserBaseinfos?: IUucUserBaseinfoMp[];
  fmpSubCompany?: IFmpSubCompanyMp;
}

export class MsgReceiverGroupMp implements IMsgReceiverGroupMp {
  constructor(
    public id?: number,
    public name?: string,
    public desc?: string,
    public uucDepartmentTrees?: IUucDepartmentTreeMp[],
    public uucUserBaseinfos?: IUucUserBaseinfoMp[],
    public fmpSubCompany?: IFmpSubCompanyMp
  ) {}
}
