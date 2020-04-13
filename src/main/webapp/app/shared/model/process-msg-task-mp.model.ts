import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';

export interface IProcessMsgTaskMp {
  id?: number;
  pushSubject?: IPushSubjectMp;
}

export class ProcessMsgTaskMp implements IProcessMsgTaskMp {
  constructor(public id?: number, public pushSubject?: IPushSubjectMp) {}
}
