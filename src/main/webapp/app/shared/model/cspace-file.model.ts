import { IGovernmentReport } from 'app/shared/model/government-report.model';

export interface ICspaceFile {
  id?: number;
  spaceId?: string;
  fileId?: string;
  fileName?: string;
  fileSize?: number;
  fileType?: string;
  governmentReport?: IGovernmentReport;
}

export class CspaceFile implements ICspaceFile {
  constructor(
    public id?: number,
    public spaceId?: string,
    public fileId?: string,
    public fileName?: string,
    public fileSize?: number,
    public fileType?: string,
    public governmentReport?: IGovernmentReport
  ) {}
}
