import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

export interface IUucDepartmentTreeMp {
  id?: number;
  msgReceiverGroups?: IMsgReceiverGroupMp[];
}

export class UucDepartmentTreeMp implements IUucDepartmentTreeMp {
  constructor(public id?: number, public msgReceiverGroups?: IMsgReceiverGroupMp[]) {}
}
