package com.projects.sky.zookeeper.entity;

import java.io.Serializable;

/**
 * 群集
 *
 */
public class Cluster implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String ip; // '节点ip'

	private String port; // '节点端口'

	private String mapIp; // '映射ip'

	private String mapPort; // '映射端口'

	private MachineStatus status; // 机器状态

	private Long nodeId;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMapIp() {
		return mapIp;
	}

	public void setMapIp(String mapIp) {
		this.mapIp = mapIp;
	}

	public String getMapPort() {
		return mapPort;
	}

	public void setMapPort(String mapPort) {
		this.mapPort = mapPort;
	}

	public MachineStatus getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public void setStatus(MachineStatus status) {
		this.status = status;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
