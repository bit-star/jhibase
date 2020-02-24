import { IServiceWindowMp } from 'app/shared/model/service-window-mp.model';

export interface IFmpSubCompanyMp {
  id?: number;
  name?: string;
  code?: string;
  adminGroupId?: string;
  ifPublic?: string;
  styleId?: string;
  isDeleted?: boolean;
  serviceWindows?: IServiceWindowMp[];
}

export class FmpSubCompanyMp implements IFmpSubCompanyMp {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public adminGroupId?: string,
    public ifPublic?: string,
    public styleId?: string,
    public isDeleted?: boolean,
    public serviceWindows?: IServiceWindowMp[]
  ) {
    this.isDeleted = this.isDeleted || false;
  }
}
