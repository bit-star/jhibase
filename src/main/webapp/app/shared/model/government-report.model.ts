import { Moment } from 'moment';
import { ICspaceFile } from 'app/shared/model/cspace-file.model';
import { GovernmentReportType } from 'app/shared/model/enumerations/government-report-type.model';

export interface IGovernmentReport {
  id?: number;
  type?: GovernmentReportType;
  reportDate?: Moment;
  objectName?: string;
  position?: string;
  contactInformation?: string;
  content?: string;
  location?: string;
  cspaceFiles?: ICspaceFile[];
}

export class GovernmentReport implements IGovernmentReport {
  constructor(
    public id?: number,
    public type?: GovernmentReportType,
    public reportDate?: Moment,
    public objectName?: string,
    public position?: string,
    public contactInformation?: string,
    public content?: string,
    public location?: string,
    public cspaceFiles?: ICspaceFile[]
  ) {}
}
