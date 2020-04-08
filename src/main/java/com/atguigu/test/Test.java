package com.atguigu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

/**
 * @author zhangzm
 * @date 2019/8/27 11:23
 */
public class Test {

	@org.junit.Test
	public void main() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
//		Integer reduce = list.stream().parallel().reduce(0, (a, b) -> {
//			System.out.println(Thread.currentThread() + " a:" + a + ",b:" + b);
//			return a + b;
//		}, (a, b) -> {
//			System.out.println(Thread.currentThread() + " 合并 a:" + a + ",b:" + b);
//			return a + b;
//		});
//		System.out.println(reduce);
		//276

//		ForkJoinPool fk = new ForkJoinPool(4);
		ForkJoinPool fk = ForkJoinPool.commonPool();
		ForkJoinTask<Integer> submit = fk.submit(new task(list));
		fk.shutdown();
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	class task extends RecursiveTask<Integer> {

		private List<Integer> list;

		public task(List<Integer> list) {
			this.list = list;
		}

		@Override
		protected Integer compute() {
			if (list.size() == 1) {
				System.out.println("return:" + Thread.currentThread());
				return list.get(0);
			} else {
				System.out.println("fork:" + Thread.currentThread());
				task left, right;
				left = new task(list.subList(0, list.size() / 2));
				right = new task(list.subList(list.size() / 2, list.size()));
				this.invokeAll(left, right);
				Integer join = left.join();
				Integer join1 = right.join();
				System.out.println("add:" + Thread.currentThread());
				return join + join1;
			}
		}
	}

	@org.junit.Test
	public void test1() {

		HashMap hashMap = new HashMap();
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		hashMap.put("1",new ArrayList(list));
		hashMap.put("2",new ArrayList(list));
		hashMap.put("3",new ArrayList(list));

		hashMap.entrySet().stream().flatMap(v -> {
			Map.Entry v1 = (HashMap.Entry) v;
			return ((List)v1.getValue()).stream();
		}).forEach(System.out::println);
	}

	@org.junit.Test
	public void test2() {
		String s = "{ "
				+ "\"AcceptDate\": \"2019-06-20 16:24:36\","
				+ "\"AcceptDocNo\": \"123\","
				+ "\"Address\": \"123\","
				+ "\"ApplyChannel\": \"1\","
				+ "\"ApplyDate\": \"2019-06-20 16:24:36\","
				+ "\"ApplyForm\": \"123\", "
				+ "\"ApplyType\": \"12\",  "
				+ "\"ApplyUerIdCode\": \"申请人统一身份认证平台账号(2000)\", "
				+ "\"ApplyerName\": \"123\",  "
				+ "\"ApplyerPageCode\": \"123\", "
				+ "\"ApplyerPageType\": \"1\","
				+ "\"ApplyerType\": \"6\", "
				+ "\"CatalogCode\": \"123\",  "
				+ "\"Cd_Batch\": \"4\", "
				+ "\"Cd_Operation\": \"13\",  "
				+ "\"Cd_Time\": \"2019-06-21 00:00:36\",  "
				+ "\"ContactCode\": \"12\","
				+ "\"ContactMobile\": \"13\", "
				+ "\"ContactName\": \"14\","
				+ "\"ContactType\": \"12\","
				+ "\"ContactUserIdCode\": \"联系人统一身份认证平台账号(2000)\", "
				+ "\"DetailURL\": \"DetailURL\", "
				+ "\"HandleUserName\": \"2\", "
				+ "\"Legal\": \"1\", "
				+ "\"LocalCatalogCode\": \"1\",  "
				+ "\"LocalTaskCode\": \"1\",  "
				+ "\"OrgCode\": \"03\", "
				+ "\"OrgName\": \"05\", "
				+ "\"SLZTDM\": \"1\","
				+ "\"BSLYY\": \"\",  "
				+ "\"ProjectCo\": \"\", "
				+ "\"ProDataOrgCode\": \"2\", "
				+ "\"ProDataOrgName\": \"1\", "
				+ "\"ProDataRegionCode\": \"3\", "
				+ "\"ProjectName\": \"4\", "
				+ "\"ProjectNo\": \"2\","
				+ "\"ProjectType\": \"5\", "
				+ "\"PromiseDate\": \"2019-06-20 16:24:36\", "
				+ "\"RegionCode\": \"1\",  "
				+ "\"RowGuid\": \"4d239bbe-781c-49d3-bfad-6e13ac738f4d\","
				+ "\"SBLSH\": \"123\",  "
				+ "\"TargetOrgName\": \"3\",  "
				+ "\"TaskCode\": \"5\", "
				+ "\"TaskHandleItem\": \"7\", "
				+ "\"TaskName\": \"事项名称小于1000\", "
				+ "\"TaskVersion\": 1,  "
				+ "\"ZipCode\": \"1\""
				+ "}";
		System.out.println(s);
	}
}
