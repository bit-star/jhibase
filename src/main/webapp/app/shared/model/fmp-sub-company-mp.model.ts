import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';

export interface IFmpSubCompanyMp {
  id?: number;
  msgReceiverGroups?: IMsgReceiverGroupMp[];
  pushSubjects?: IPushSubjectMp[];
}

export class FmpSubCompanyMp implements IFmpSubCompanyMp {
  constructor(public id?: number, public msgReceiverGroups?: IMsgReceiverGroupMp[], public pushSubjects?: IPushSubjectMp[]) {}
}
