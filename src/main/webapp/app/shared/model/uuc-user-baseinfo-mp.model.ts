import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

export interface IUucUserBaseinfoMp {
  id?: number;
  msgReceiverGroups?: IMsgReceiverGroupMp[];
}

export class UucUserBaseinfoMp implements IUucUserBaseinfoMp {
  constructor(public id?: number, public msgReceiverGroups?: IMsgReceiverGroupMp[]) {}
}
